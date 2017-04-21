/**
 * Created by c-mihapeti on 4/11/2017.
 */
class Model_data {




    boolean vm_status
    String vm_name, vm_username, vm_pass

    def local_path_file=[] as ArrayList<String>
    def vm_path=[] as ArrayList<String>
    def file_name=[] as ArrayList<String>

    Model_data(boolean status,String name,String username,String pass){
        this.vm_status=status
        this.vm_name=name
        this.vm_username=username
        this.vm_pass=pass
    }

    Model_data(){

    }

    boolean getVm_status() {
        return vm_status
    }

    void setVm_status(boolean vm_status) {
        this.vm_status = vm_status
    }

    String getVm_name() {
        return vm_name
    }

    void setVm_name(String vm_name) {
        this.vm_name = vm_name
    }

    String getVm_username() {
        return vm_username
    }

    void setVm_username(String vm_username) {
        this.vm_username = vm_username
    }

    String getVm_pass() {
        return vm_pass
    }

    void setVm_pass(String vm_pass) {
        this.vm_pass = vm_pass
    }


    ArrayList getLocal_path_file() {
        return local_path_file
    }

    void setLocal_path_file(ArrayList arrayList) {
       this.local_path_file=arrayList
    }


    ArrayList getvm_path() {
        return vm_path
    }

    void setvm_path(ArrayList arrayList) {
        this.vm_path=arrayList
    }


    ArrayList getfile_name() {
        return file_name
    }

    void setfile_name(ArrayList arrayList) {
        this.file_name=arrayList
    }



}
