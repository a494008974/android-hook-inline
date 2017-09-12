//
// Created by Administrator on 2016/12/30.
//

#ifndef MVIDEO_CORE_H
#define MVIDEO_CORE_H

 int inject_remote_process(pid_t target_pid, const char *library_path, const char *function_name, const char *param, size_t param_size);
 void* get_module_base(pid_t pid, const char* module_name);

#endif //MVIDEO_CORE_H
