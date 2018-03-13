@echo off
set /p filepath=Enter sensor data file path(DATA.txt):
if [%filepath%]==[] (set filepath=DATA.txt)

set /p phost=Enter postgres host(default localhost):
if [%phost%]==[] (set phost=localhost)
set /p pport=Enter postgres port(default 5432):
if [%pport%]==[] (set pport=5432)
set /p pdatabasename=Enter postgres databasename(default ias):
if [%pdatabasename%]==[] (set pdatabasename=ias)
set /p pusername=Enter postgres username(default postgres):
if [%pusername%]==[] (set pusername=postgres)
set /p ppassword=Enter postgres password(default postgres):
if [%ppassword%]==[] (set ppassword=postgres)

set /p mhost=Enter echange zone host(default localhost):
if [%mhost%]==[] (set mhost=localhost)
set /p mport=Enter echange zone port(default 3306):
if [%mport%]==[] (set mport=3306)
set /p mdatabasename=Enter echange zone databasename(default exchange_zone_gunib):
if [%mdatabasename%]==[] (set mdatabasename=exchange_zone_gunib)
set /p musername=Enter echange zone username(default root):
if [%musername%]==[] (set musername=root)
set /p mpassword=Enter echange zone password(default root):
if [%mpassword%]==[] (set mpassword=root)

set /p ppath=Enter path to installed postgres folder(default C:\Program Files\PostgreSQL\9.6):
if ["%ppath%"]==[""] (set ppath=C:\Program Files\PostgreSQL\9.6)

cd ..

echo spring.freemarker.cache: false>application.properties

echo app.datasource.main.url=jdbc:postgresql://%phost%:%pport%/%pdatabasename%>>application.properties
echo app.datasource.main.username=%pusername%>>application.properties
echo app.datasource.main.password=%ppassword%>>application.properties

echo app.datasource.exchange.url=jdbc:mysql://%mhost%:%mport%/%mdatabasename%>>application.properties
echo app.datasource.exchange.username=%musername%>>application.properties
echo app.datasource.exchange.password=%mpassword%>>application.properties
echo app.datasource.exchange.test-while-idle=true>>application.properties
echo app.datasource.exchange.testOnBorrow=true>>application.properties
echo app.datasource.exchange.validationQuery=SELECT 1>>application.properties

echo spring.jpa.properties.javax.persistence.sharedCache.mode=ALL>>application.properties

echo spring.jackson.serialization.write_dates_as_timestamps=false>>application.properties

echo get.task.time=30000>>application.properties
echo process.task.time=15000>>application.properties
echo process.file.time=60000>>application.properties
echo file.path=%filepath%>>application.properties

echo logging.file=myapplication.log>>application.properties

echo logging.level.org.springframework.web=INFO>>application.properties
echo logging.level.org.hibernate=ERROR>>application.properties

SET PGCLIENTENCODING=utf-8
SET PGPASSWORD=%ppassword%

cd sql
echo Please retype postgres password
"%ppath%\bin\createdb.exe" -U %pusername% -W -E UTF-8  %pdatabasename%
"%ppath%\bin\psql.exe" -U %pusername% -d %pdatabasename% -f drop.ddl.sql
"%ppath%\bin\psql.exe" -U %pusername% -d %pdatabasename% -f create.ddl.sql
"%ppath%\bin\psql.exe" -U %pusername% -d %pdatabasename% -f data.ddl.sql
"%ppath%\bin\psql.exe" -U %pusername% -d %pdatabasename% -f function.sql
"%ppath%\bin\psql.exe" -U %pusername% -d %pdatabasename% -f triggers.sql