package com.getjavajob.bezfamilnyydg.dao.Utils;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;

public class Initializer {
    public static Blob initBlobWithCloseConnection(byte[] data, Connection connection) throws SQLException {
        try {

            if (data == null || data.length == 0) {
                return null;
            }
            Blob blob = connection.createBlob();
            blob.setBytes(1, data);
            return blob;

        } finally {
            connection.close();
        }
    }

    public static Blob initBlob(byte[] data, Connection connection) throws SQLException {
        if (data == null || data.length == 0) {
            return null;
        }
        Blob blob = connection.createBlob();
        blob.setBytes(1, data);
        return blob;
    }

}
