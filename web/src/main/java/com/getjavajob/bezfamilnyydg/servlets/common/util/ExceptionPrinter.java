package com.getjavajob.bezfamilnyydg.servlets.common.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionPrinter {
    public static void printAndSendError(HttpServletResponse response, int codeOfError, Exception e) {
        try {
            e.printStackTrace();
            response.sendError(codeOfError);
        } catch (IOException e1) {
            System.err.println("**********Exception during HttpServletResponse.sendError() method");
            e1.printStackTrace();
        }
    }
}
