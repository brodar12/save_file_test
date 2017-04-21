/**
 * Created by c-mihapeti on 4/13/2017.
 */
class Model_data_git {

    String repository_path, repository_url, repository_name, repository_git_username, repository_git_email

    String getRepository_git_username() {
        return repository_git_username
    }

    void setRepository_git_username(String repository_git_username) {
        this.repository_git_username = repository_git_username
    }

    String getRepository_git_email() {
        return repository_git_email
    }

    void setRepository_git_email(String repository_git_email) {
        this.repository_git_email = repository_git_email
    }


    String getRepository_name() {
        return repository_name
    }

    void setRepository_name(String repository_name) {
        this.repository_name = repository_name
    }

    String getRepository_path() {
        return repository_path
    }

    void setRepository_path(String repository_path) {
        this.repository_path = repository_path
    }

    String getRepository_url() {
        return repository_url
    }

    void setRepository_url(String repository_url) {
        this.repository_url = repository_url
    }
}
