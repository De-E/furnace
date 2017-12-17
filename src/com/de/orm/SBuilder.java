/*
 * Copyright (C) 2017 Raffaele Francesco Mancino.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package com.de.orm;

import java.sql.SQLXML;
import java.util.ArrayList;
import com.de.orm.dal.DatabaseManager;

/**
 *
 * @author Raffaele Francesco Mancino
 */
public class SBuilder
{
    private DatabaseManager databaseManager;
    private Statement statement = new Statement();
    
    public SBuilder(DatabaseManager dm)
    {
        this.databaseManager=dm;
    }
    
    private SBuilder onTable(String tbl)
    {
        this.statement.table=tbl;
        return this;
    }
    
    public SBuilder insertInto(String tbl)
    {
        this.onTable(tbl);
        return this;
    }
    
    public SBuilder deleteFrom(String tbl)
    {
        this.onTable(tbl);
        return this;
    }
    
    public SBuilder update(String tbl)
    {
        this.onTable(tbl);
        return this;
    }
    
    public SBuilder values(String value)
    {
        if(this.statement.values==null)
            this.statement.values=new ArrayList<String>();
        this.statement.values.add(value);
        return this;
    }
    
    public SBuilder where(String condition)
    {
        this.statement.where=condition;
        return this;
    }
    
    public SBuilder set(String attribute)
    {
        this.statement.set=attribute;
        return this;
    }
    
    @Override
    public String toString()
    {
        return this.statement.toString();
    }
    
    public SBuilder showTerminal()
    {
        System.out.println(this.statement.toString());
        return this;
    }
    
    public void exec()
    {
        this.databaseManager.statement(this.statement.toString());
    }
}
