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

import java.util.ArrayList;

/**
 *
 * @author Raffaele Francesco Mancino
 */
public class Statement
{
    public String table=null;
    public ArrayList<String> values=null;
    public String where=null;
    public String set=null;

    @Override
    public String toString()
    {
        String statement="";
        if(this.values!=null)
        {
            statement = "INSERT INTO " + this.table + "\nVALUES ";
            for(int i=0; i<this.values.size();i++)
            {
                statement += "(" +this.values.get(i)+ ")";
                if (i<this.values.size()-1)
                {
                    statement += ",\n";
                }
            }
        }else if (this.set!=null)
        {
            statement = "UPDATE " + this.table + " SET " + this.set;
            if(this.where!=null)
            {
                statement += " WHERE " + this.where;
            }
        }else{
            statement = "DELETE FROM " + this.table + " WHERE " + this.where;
        }
        return statement;
    }
    
}
