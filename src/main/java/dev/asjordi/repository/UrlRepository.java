package dev.asjordi.repository;

import dev.asjordi.configs.MysqlConnection;
import dev.asjordi.configs.Repository;
import dev.asjordi.models.Url;
import jakarta.inject.Inject;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Repository
public class UrlRepository implements IRepository<Url> {

    @Inject
    @MysqlConnection
    private Connection conn;

    @Override
    public List<Url> findAll() throws Exception {
        List<Url> urls = new LinkedList<>();

        try (var stmt = conn.createStatement();
            var rs = stmt.executeQuery("SELECT * FROM urls;")) {
            while (rs.next()) {
                urls.add(createUrl(rs));
            }
        }

        return urls;
    }

    @Override
    public Url findById(Integer id) throws Exception {
        Url url = null;

        try (var ps = conn.prepareStatement("SELECT * FROM urls WHERE id = ?;")) {
            ps.setInt(1, id);
            try (var rs = ps.executeQuery()) {
                if (rs.next()) url = createUrl(rs);
            }
        }

        return url;
    }

    @Override
    public Url findByHash(String hash) throws Exception {
        Url url = null;

        try (var ps = conn.prepareStatement("SELECT * FROM urls WHERE hash = ?;")) {
            ps.setString(1, hash);
            try (var rs = ps.executeQuery()) {
                if (rs.next()) url = createUrl(rs);
            }
        }

        return url;
    }

    @Override
    public void save(Url url) throws Exception {
        String sql = "INSERT INTO urls (url, hash, created) VALUES (?, ?, ?);";

        try (var ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, url.getUrl());
            ps.setString(2, url.getHash());
            ps.setDate(3, Date.valueOf(url.getCreated()));

            ps.executeUpdate();

            if (url.getId() == null) {
                try (var rs = ps.getGeneratedKeys()) {
                    if (rs.next()) url.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (var ps = conn.prepareStatement("DELETE FROM urls WHERE id = ?;")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Url createUrl(ResultSet rs) throws Exception {
        Url url = new Url();
        url.setId(rs.getInt("id"));
        url.setUrl(rs.getString("url"));
        url.setHash(rs.getString("hash"));
        url.setCreated(rs.getDate("created").toLocalDate());
        return url;
    }
}
