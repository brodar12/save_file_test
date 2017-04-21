@ECHO OFF

ECHO Git clone distribute vm
cd /D C:\
mkdir save_temporar_546544
cd save_temporar_546544

git clone ${git_url}

${generate_command}

cd ..
rd /s/q save_temporar_546544
rd /s/q save_temporar_546544







