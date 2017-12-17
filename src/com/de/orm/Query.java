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

/**
 *
 * @author Raffaele Francesco Mancino
 */
public class Query
{
    public String select=null;
    public String from=null;
    public String where=null;
    public String groupBy=null;
    public String having=null;
    public String orderBy=null;
    public String limit=null;
    
    @Override
    public String toString()
    {
        String query="SELECT "+this.select+"FROM "+this.from;
        if(this.where!=null && this.where!="")
            query += "WHERE "+this.where;
        if(this.groupBy!=null && this.groupBy!="")
            query += "GROUP BY "+this.groupBy;
        if(this.having!=null && this.having!="")
            query += "HAVING "+this.having;
        if(this.orderBy!=null && this.orderBy!="")
            query += "ORDER BY "+this.orderBy;
        if(this.limit!=null && this.limit!="")
            query += "LIMIT "+this.limit;
        return query;
    }
}
