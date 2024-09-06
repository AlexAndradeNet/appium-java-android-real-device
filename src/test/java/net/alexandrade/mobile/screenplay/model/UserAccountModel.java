/*
nuvei.com CONFIDENTIAL

Copyright (c) 2024. All Rights Reserved.

NOTICE: The source code contained or described herein and all documents
related to the source code ("Material") are owned by Nuvei Inc.
or its companies, suppliers or licensors.

Dissemination of this information or reproduction of this material
is strictly forbidden unless prior written permission is obtained
from Nuvei Inc.
*/
package net.alexandrade.mobile.screenplay.model;

import java.util.Objects;

public class UserAccountModel {
    private final String loginId;
    private final String email;

    public String getLoginID() {
        return loginId;
    }

    public String getEmail() {
        return email;
    }

    public UserAccountModel() {
        this.loginId = "";
        this.email = "";
    }

    public UserAccountModel(String title, String email) {
        this.loginId = title;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Login ID: " + getLoginID() + ", E-Mail: " + getEmail();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        UserAccountModel userAccountModel = (UserAccountModel) obj;

        if (!Objects.equals(loginId, userAccountModel.loginId)) return false;
        return Objects.equals(email, userAccountModel.email);
    }

    @Override
    public int hashCode() {
        int result = loginId != null ? loginId.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public static class UserAccountBuilder {
        private String loginId = "";
        private String email = "";

        public UserAccountBuilder() {}

        public UserAccountBuilder called(String loginId) {
            this.loginId = loginId;
            return this;
        }

        public UserAccountBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserAccountModel build() {
            return new UserAccountModel(loginId, email);
        }
    }
}
