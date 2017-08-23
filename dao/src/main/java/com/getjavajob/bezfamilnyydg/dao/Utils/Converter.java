package com.getjavajob.bezfamilnyydg.dao.Utils;

import com.getjavajob.bezfamilnyydg.dao.exceptions.DAOException;

import java.sql.Blob;
import java.sql.SQLException;


public class Converter {
    public static byte[] blobToByteArray(Blob blob) throws SQLException {
            if (blob == null) {
                return null;
            }
            return blob.getBytes(1, (int) blob.length());
    }
}
