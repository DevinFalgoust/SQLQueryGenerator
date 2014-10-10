SQLQueryGenerator
==========

This is a simple program created to generate a large amount of test SQL Queries very quickly.
Currently only the Insert Query Generator is completely implemented.

The Select Query Generator has its basic functionality built, but is not feature complete.
It currently does not support the OR operator, the BETWEEN or IN operators, multiple values in
a given where clause, and it currently does not provide type safety on any where operators.
It also does not support Aliases or Joins of any kind.

Instructions on how to use this are included in the Driver.java file

Future plans include:
- Finish Select Query Generator
- Add Update Query Generator
- Add Delete Query Generator
- Add Database Management Query Generator
-- Create Database Functionality
-- Create Table Functionality
-- Drop Database Functionality
-- Drop Table Functionality
-- Alter Table Functionality
-- Truncate Table Functionality

A class in this project (InsertQueryGenerator) includes the randomAlphabetic function from 
org.apache.commons.lang3.RandomStringUtils
You can either go download this from the Apache Commons website and add it to your Build Path,
or write your own random string generation function.

==========

Copyright 2014 Devin Falgoust

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
