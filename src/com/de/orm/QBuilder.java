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

import java.sql.ResultSet;
import com.de.orm.dal.DatabaseManager;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raffaele Francesco Mancino
 */
public class QBuilder
{
    private DatabaseManager databaseManager;
    private Query query=new Query(); //DML language
    
    public QBuilder(DatabaseManager dm)
    {
        this.databaseManager=dm;
    }
    
    public QBuilder select(String str)
    {
        this.query.select=str+" ";
        return this;
    }
    
    public QBuilder selectDistinct(String str)
    {
        this.query.select="DISTINCT "+str+" ";
        return this;
    }
    
    public QBuilder from(String str)
    {
        this.query.from=str+" ";
        return this;
    }
    
    public QBuilder join(String tbl, String cond)
    {
        this.query.from+="JOIN "+tbl+" ON "+cond+" "; 
        return this;
    }
    
    public QBuilder join(QBuilder tbl, String cond)
    {
        this.query.from+="JOIN ("+tbl.toString()+") ON "+cond+" "; 
        return this;
    }
    
    public QBuilder leftJoin(String tbl, String cond)
    {
        this.query.from+="LEFT ";
        this.join(tbl, cond);
        return this;
    }
    
    public QBuilder leftJoin(QBuilder tbl, String cond)
    {
        this.query.from+="LEFT ";
        this.join(tbl, cond);
        return this;
    }
    
    public QBuilder rightJoin(String tbl, String cond)
    {
        this.query.from+="RIGHT ";
        this.join(tbl, cond);
        return this;
    }
    
    public QBuilder rightJoin(QBuilder tbl, String cond)
    {
        this.query.from+="RIGHT ";
        this.join(tbl, cond);
        return this;
    }
    
    public QBuilder fullJoin(String tbl, String cond)
    {
        this.query.from+="FULL ";
        this.join(tbl, cond);
        return this;
    }
    
    public QBuilder fullJoin(QBuilder tbl, String cond)
    {
        this.query.from+="FULL ";
        this.join(tbl, cond);
        return this;
    }
    
    public QBuilder naturalJoin(String tbl, String cond)
    {
        this.query.from+="NATURAL ";
        this.join(tbl, cond);
        return this;
    }
    
    public QBuilder naturalJoin(QBuilder tbl, String cond)
    {
        this.query.from+="NATURAL ";
        this.join(tbl, cond);
        return this;
    }
    
    public QBuilder where(String str)
    {
        this.query.where=str+" ";
        return this;
    }
    
    public QBuilder andWhere(String str)
    {
        this.query.where+="AND "+str+" ";
        return this;
    }
    
    public QBuilder orWhere(String str)
    {
        this.query.where+="OR "+str+" ";
        return this;
    }
    
    public QBuilder group(String str)
    {
        this.query.groupBy=str+" ";
        return this;
    }
    
    public QBuilder having(String str)
    {
        this.query.having=str+" ";
        return this;
    }
    
    public QBuilder order(String str)
    {
        this.query.orderBy=str+" ";
        return this;
    }
    
    public QBuilder limit(String str)
    {
        this.query.limit=str+" ";
        return this;
    }
    
    public ArrayList exec(Class tblClass)
    {
        ResultSet resultSet = this.databaseManager.query(this.query.toString());
        
        Boolean fillable = this.isFillable(tblClass, resultSet);
        if (fillable)
        {
            try
            {
                return this.fill(tblClass, resultSet);
            } catch (IllegalAccessException ex)
            {
                Logger.getLogger(QBuilder.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex)
            {
                Logger.getLogger(QBuilder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return null;
    }
    
    private boolean isFillable(Class object, ResultSet resultSet)
    {
        boolean test = true;
        try
        {
            for (int i=1; i<=resultSet.getMetaData().getColumnCount(); i++)            
            {
                String CName = resultSet.getMetaData().getColumnName(i);
                try
                {
                    object.getField(CName);
                    
                } catch (NoSuchFieldException ex)
                {
                    Logger.getLogger(QBuilder.class.getName()).log(Level.SEVERE, null, ex);
                    test = false;
                } catch (SecurityException ex)
                {
                    Logger.getLogger(QBuilder.class.getName()).log(Level.SEVERE, null, ex);
                    test = false;
                } 
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(QBuilder.class.getName()).log(Level.SEVERE, null, ex);
            test = false;
        }
        return test;
    }
    
    private ArrayList<Object> fill(Class tblClass, ResultSet resultSet) throws IllegalAccessException, InstantiationException
    {
        ArrayList<Object> list = new ArrayList<Object>();
        Field[] fields = tblClass.getFields();
        
        try
        {
            while (resultSet.next())
            {
                Object object = tblClass.newInstance();
                for (int i=1; i<=resultSet.getMetaData().getColumnCount(); i++)
                {
                    Field field = fields[i-1];
                    
                    String CName = resultSet.getMetaData().getColumnName(i);
                    field.set(object, resultSet.getObject(CName, field.getType()));
                }
                list.add(object);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(QBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    @Override
    public String toString()
    {
        return this.query.toString();
    }
    
    public QBuilder showTerminal()
    {
        System.out.println(this.query.toString());
        return this;
    }
}
