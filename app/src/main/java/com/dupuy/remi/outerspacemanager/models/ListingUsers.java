package com.dupuy.remi.outerspacemanager.models;

import java.util.List;

/**
 * Created by lledent on 23/01/2018.
 */

public class ListingUsers {
    public ListingUsers(List<UserScore> users) {
        this.users = users;
    }

    public List<UserScore> getUsers() {
        return users;
    }

    public void setUsers(List<UserScore> users) {
        this.users = users;
    }

    private List<UserScore> users;

}
