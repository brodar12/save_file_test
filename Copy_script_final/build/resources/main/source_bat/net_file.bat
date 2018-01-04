@ECHO OFF



if "${mem}"=="true" (
    ECHO Create temporar repository
    cd /D ${directory}
    mkdir save_temporar_repo_git
    cd save_temporar_repo_git
    git clone ${git_repo_ssh}

    ECHO Delete git folder
    for /f %%f in ('dir /b ${directory}save_temporar_repo_git\${local_repository}\') do (
     if NOT "%%f"=="README.md" (
      rd /s/q ${directory}save_temporar_repo_git\${local_repository}\%%f
      del ${directory}save_temporar_repo_git\${local_repository}\%%f
      )
    )

    ECHO Git delete and push
    cd ${directory}save_temporar_repo_git\${local_repository}\
    git add .
    git commit -m "delete directory"
    git push
)

if not "${mem}"=="true" (
    ECHO Git add and push
    cd ${directory}save_temporar_repo_git\${local_repository}\
    git add .
    git commit -m "add directory"
    git status
    git push
    git status
    cd /D ${directory}
    rd /s/q save_temporar_repo_git
)
