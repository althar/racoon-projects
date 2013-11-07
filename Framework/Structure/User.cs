﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OwlBusinessStudioLib.DB
{
    public class User
    {
        public string FirstName;
        public string SecondName;
        public string LastName;
        public string EMail;
        public string Phone;
        public Roles Role;
        public int ID;

        public User(int id, string firstName, string secondName, string lastName, string email, string phone, Roles role)
        {
            FirstName = firstName;
            SecondName = secondName;
            LastName = lastName;
            EMail = email;
            Phone = phone; 
            Role = role;
            ID = id;
        }
    }
    public enum Roles
    {
        Admin = 1,
        Operator = 2,
        Driver = 3,
        Viewer = 4
    }
}