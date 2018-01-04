@ECHO OFF



if "false"=="true" (
    ECHO Create temporar repository
    cd /D D:\
    mkdir save_temporar_repo_git
    cd save_temporar_repo_git
    git clone git@github.com:brodar12/save_copy_file.git

    ECHO Delete git folder
    for /f %%f in ('dir /b D:\save_temporar_repo_git\save_copy_file\') do (
     if NOT "%%f"=="README.md" (
      rd /s/q D:\save_temporar_repo_git\save_copy_file\%%f
      del D:\save_temporar_repo_git\save_copy_file\%%f
      )
    )

    ECHO Git delete and push
    cd D:\save_temporar_repo_git\save_copy_file\
    git add .
    git commit -m "delete directory"
    git push
)

if not "false"=="true" (
    ECHO Git add and push
    cd D:\save_temporar_repo_git\save_copy_file\
    git add .
    git commit -m "add directory"
    git status
    git push origin master
    git status
    cd /D D:\
    rd /s/q save_temporar_repo_git
)
