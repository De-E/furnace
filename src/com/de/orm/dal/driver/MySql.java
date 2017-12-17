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
package com.de.orm.dal.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.de.orm.ConnectionParam;

/**
 *
 * @author Raffaele Francesco Mancino
 */
public class MySql implements IConnection
{
    private Connection connection;
    private Statement statement;

    
    @Override
    public boolean connect(ConnectionParam connectionParam) {
        String url="jdbc:mysql://" + connectionParam.getHost() +
                "/" + connectionParam.getDatabase() +
                "?user=" + connectionParam.getUser() +
                "&password=" + connectionParam.getPassword();
        try {
            this.connection=DriverManager.getConnection(url);
            this.statement=this.connection.createStatement();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
    @Override
    public ResultSet query(String str) {
        try {
            return this.statement.executeQuery(str);
        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    
    @Override
    public void statement(String str) {
        try {
            this.statement.execute(str);
        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public void disconnect() {
        try {
            this.connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
