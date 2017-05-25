@ECHO OFF

cd  /D ${directory}save_temporar_repo_git\${local_repository}\

${delete_HEAD}
git push origin +master

