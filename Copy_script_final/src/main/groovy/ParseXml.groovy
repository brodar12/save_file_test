import sun.rmi.runtime.Log

/**
 * Created by c-mihapeti on 3/13/2017.
 */
class ParseXml {


    def list_data_connect=[] as ArrayList<Model_data>
    def xml=new XmlSlurper().parse("src/main/resources/config_xml/vm_setings.xml")
    Model_data data_connect=new Model_data()
    Model_data_git data_git_repo=new Model_data_git()
    String save_mem_tem_file=""




 void create_object_vm(){

     def incre=0,incre1=0
     //create vm_setings object
     println("Generate vm connection object. ")
     while(xml.connectsettings.VM_setings[incre].vm_name!=""){
         def local1=[] as ArrayList<String>
         def local2=[] as ArrayList<String>
         def local3=[] as ArrayList<String>

        data_connect.setVm_status(new Boolean("${xml.connectsettings.VM_setings[incre].vm_status}"))
         data_connect.setVm_name("${xml.connectsettings.VM_setings[incre].vm_name}")
         data_connect.setVm_username("${xml.connectsettings.VM_setings[incre].vm_username}")
         data_connect.setVm_pass("${xml.connectsettings.VM_setings[incre].vm_pass}")

         while (xml.connectsettings.VM_setings[incre].send_files[incre1].local_path_file!=""){
              local1.add("${xml.connectsettings.VM_setings[incre].send_files[incre1].local_path_file}")
             local2.add("${xml.connectsettings.VM_setings[incre].send_files[incre1].vm_path}")
             local3.add(get_file_name_in_path("${xml.connectsettings.VM_setings[incre].send_files[incre1].local_path_file}"))
             ++incre1
         }
         incre1=0
         ++incre
         data_connect.setLocal_path_file(local1)
         data_connect.setvm_path(local2)
         data_connect.setfile_name(local3)
         list_data_connect.add(data_connect)
         data_connect=new Model_data()
     }

    //create git object
     println("Generate git connection object. ")
     data_git_repo.setRepository_path("${xml.connectsettings.git_reposytory.repo_path}")
     data_git_repo.setRepository_url("${xml.connectsettings.git_reposytory.repo_url}")
     data_git_repo.setRepository_ssh("${xml.connectsettings.git_reposytory.repo_ssh}")
     data_git_repo.setRepository_name("${xml.connectsettings.git_reposytory.repo_name}")
     data_git_repo.setRepository_git_username("${xml.connectsettings.git_reposytory.git_username}")
     data_git_repo.setRepository_git_email("${xml.connectsettings.git_reposytory.git_email}")
    }




    void add_file_repo_objects(){
        println("Add file to local reosytory.")
        for(def i=0;i<list_data_connect.size();++i) {

            if (list_data_connect.get(i).getVm_status()!=false) {

                create_git_local_reposytory_objects("true")

                println("Create  file to local reosytory:" + data_git_repo.getRepository_path() + "save_temporar_repo_git\\" + data_git_repo.getRepository_name() + "\\" + list_data_connect.get(i).getVm_name())
                new File(data_git_repo.getRepository_path() + "save_temporar_repo_git\\" + data_git_repo.getRepository_name() + "\\" + list_data_connect.get(i).getVm_name()).mkdir()

                for (def j = 0; j < list_data_connect.get(i).local_path_file.size(); ++j) {

                    if (execute_cmd("dir /b " + list_data_connect.get(i).local_path_file.get(j)) != "") {
                        new File(data_git_repo.getRepository_path() + "save_temporar_repo_git\\" + data_git_repo.getRepository_name() + "\\" + list_data_connect.get(i).getVm_name() + "\\" + j).mkdir()
                        execute_cmd("copy " + list_data_connect.get(i).local_path_file.get(j) + " " + data_git_repo.getRepository_path() + "save_temporar_repo_git\\" + data_git_repo.getRepository_name() + "\\" + list_data_connect.get(i).getVm_name() + "\\" + j)

                        generate_distribute_cmd(list_data_connect.get(i).getVm_name(), j, list_data_connect.get(i).file_name.get(j), list_data_connect.get(i).vm_path.get(j))
                    } else {
                        println("In this path " + list_data_connect.get(i).local_path_file.get(j) + " dont exist file " + list_data_connect.get(i).file_name.get(j))
                    }
                }

                   create_git_local_reposytory_objects("false")
                //  populate_temporar_distribute_file()
                //  save_mem_tem_file=""
                // connection_vm(list_data_connect.get(i).getVm_name(),list_data_connect.get(i).getVm_username(),list_data_connect.get(i).getVm_pass())
            }
            else println("machine:"+list_data_connect.get(i).getVm_name()+ "is not use!!!")


        }


    }



    void create_git_local_reposytory_objects(String acces_var){

        def file=new File("src/main/resources/source_bat/net_file.bat")

        println("Create local reosytory.")

        def file_save=file.getText()
        file_save=file_save.replace('${directory}',data_git_repo.getRepository_path())
        file_save=file_save.replace('${git_repo_ssh}',data_git_repo.getRepository_ssh())
        file_save=file_save.replace('${local_repository}',data_git_repo.getRepository_name())
        file_save=file_save.replace('${mem}',acces_var)

        def file_temporar= new File("src/main/resources/source_bat/net_file_1.bat")
        file_temporar.write(file_save)
        System.out.println(execute_cmd(file_temporar.getAbsolutePath().toString()));
        file_temporar.delete()

    }





    void generate_distribute_cmd(String vm_name,def val_incre,String file_name,String distribute_vm_path){
        save_mem_tem_file+=" \n del "+distribute_vm_path+""+file_name+"\n"+"copy C:\\save_temporar_546544\\"+data_git_repo.getRepository_name()+"\\"+vm_name+"\\"+val_incre+"\\"+file_name+" "+distribute_vm_path
    }




  void populate_temporar_distribute_file(){

        def file=new File("src/main/resources/source_bat/distribution_file.bat")

        def file_save=file.getText()
        file_save=file_save.replace('${git_url}',data_git_repo.getRepository_url())
        file_save=file_save.replace('${generate_command}',save_mem_tem_file)

        def file_temporar1= new File("src/main/resources/source_bat/distribution_file_1.bat")
        file_temporar1.write(file_save)

    }





    void connection_vm(String vm_name, String vm_username, String vm_pass){

        def file_copy= new File("src/main/resources/source_bat/distribution_file_1.bat")
        def cmd_generate="psexec \\\\"+vm_name+" -u  "+vm_username+" -p "+vm_pass+" /c "+file_copy.getAbsolutePath().toString()+""

        println cmd_generate
        execute_cmd(cmd_generate)

        file_copy.delete()

     }



     String get_file_name_in_path(def path){
         def var1=""
        for(def i=path.length()-1;i>=0;--i){
           if(path.getAt(i)=="\\" && i<path.length()-1)break
               else if(path.getAt(i)!="\\" )var1+=path.getAt(i)
        }
         println()
         return var1.reverse()
      }


    String execute_cmd(String cmd_msg){
        return Runtime.getRuntime().exec("cmd /c "+ cmd_msg).getText()
    }



}
