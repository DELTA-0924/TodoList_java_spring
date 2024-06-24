package com.project.TodoList.services.validators;

import com.project.TodoList.common.Utility;
import com.project.TodoList.common.enums.Status;
import com.project.TodoList.common.exception.MainException;
import com.project.TodoList.models.entities.TaskEntity;
import org.springframework.http.HttpStatus;

import java.util.function.Function;

import static com.project.TodoList.common.enums.ExceptionsCode.*;

public interface TaskValidator extends Function<TaskEntity,Boolean> {
    default TaskValidator and(TaskValidator other) {
    return  num->{
        boolean result = this.apply(num);
        return result && other.apply(num);
        };
    }
    static TaskValidator TaskValid(){
        return task->{
            if(task==null)
                throw new MainException(TASK_NULL,HttpStatus.BAD_REQUEST);
            return true;
        };
    }
    static TaskValidator TaskIdValid(){
        return task->{
          if(task.id==0 || task.id==null)
              throw new MainException(ID_FIELD_NULL,HttpStatus.BAD_REQUEST);
          return true;
        };
    }

    static TaskValidator TaskTitleValid(){
        return task->{
            if(task.title.isEmpty()|| task.title==null)
                throw new MainException(TITLE_NULL,HttpStatus.BAD_REQUEST);
            if(task.title.length()>Utility.MAX_TITLE_LENGTH)
                throw new MainException(TITLE_TOO_LONG,HttpStatus.BAD_REQUEST);
            return true;
        };
    }
    static TaskValidator TaskContentValid(){
        return task->{
            if(task.content.isEmpty()|| task.content==null)
                throw new MainException(CONTENT_NULL,HttpStatus.BAD_REQUEST);
            if(task.content.length()> Utility.MAX_CONTENT_LENGTH)
                throw new MainException(CONTENT_TOO_LONG,HttpStatus.BAD_REQUEST);
            return true;
        };
    }
    static TaskValidator TaskStatusValid(){
        return task->{
            if(task.status.isEmpty())
                throw new MainException(STATUS_NULL,HttpStatus.BAD_REQUEST);
            try{
                Status.valueOf(task.status);
            }catch (Exception ex){
                throw new MainException(STATUS_ILLEGAL_ARGUMENT,HttpStatus.BAD_REQUEST);
            }
            return true;
        };
    }
}
