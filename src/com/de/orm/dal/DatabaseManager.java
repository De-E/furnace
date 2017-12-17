/* 
 * Copyright (C) 2017 Raffaele Francesco Mancino
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.de.orm.dal;

import java.sql.ResultSet;
import com.de.orm.ConnectionParam;
import com.de.orm.dal.driver.IConnection;
import com.de.orm.dal.driver.MySql;
import com.de.orm.dal.driver.PostgreSQL;

/**
 *
 * @author Raffaele Francesco Mancino
 */
public class DatabaseManager
{
    public static final String SQLite = "sqlite";
    public static final String MySQL = "mysql";
    public static final String PostgreSQL = "postgresql";
    
    private IConnection db;
    public DatabaseManager(String type)
    {
        switch(type)
        {
            case SQLite:
                this.db=null;
                break;
            case MySQL:
                this.db=new MySql();
                break;
            case PostgreSQL:
                this.db=null;
                break;
        }
    }
    
    public void connect(ConnectionParam connectionParam)
    {
        this.db.connect(connectionParam);
    }
    
    public ResultSet query(String str)
    {
        return this.db.query(str);
    }
    
    public void statement(String str)
    {
        this.db.statement(str);
    }
    
    public void disconnect()
    {
        this.db.disconnect();
    }
}
