@ECHO OFF

ECHO Open 
hostname


ECHO Mount disc 
cd /D C:

ECHO Create temporar directory
mkdir C:\test_file_repo_save\


ECHO Mount temporar directory
cd C:\test_file_repo_save\

ECHO Git clone 
git clone https://github.com/brodar12/save_file_test.git

ECHO mount git directory
cd save_file_test

ECHO show all dir 
dir

cd ../..

ECHO show all dir 
dir

ECHO Delete directory
rd /s/q test_file_repo_save



