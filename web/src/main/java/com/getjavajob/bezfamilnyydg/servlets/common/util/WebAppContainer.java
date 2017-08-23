package com.getjavajob.bezfamilnyydg.servlets.common.util;

import com.getjavajob.bezfamilnyydg.service.interfaces.AccountService;

public class WebAppContainer {
    private static AccountService accountService;

    public static AccountService getAccountService() {
        return accountService;
    }

    public static void setAccountService(AccountService accountServiceForSet) {
        accountService = accountServiceForSet;
    }
}
