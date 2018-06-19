package com.example.tanya.sqlitetask;

public class ListItem
{
    private String name, lname, branch,id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override

    public String toString() {
        return "ListItem{" +
                "name='" + name + '\'' +
                ", lname='" + lname + '\'' +
                ", branch='" + branch + '\'' +
                '}';
    }

    public ListItem(String id, String name, String lname, String branch)
    {
        this.id = id;
        this.name = name;
        this.lname = lname;
        this.branch = branch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
