package example.server.service.main;

import example.server.payload.secondary.CustomResponse;
import example.server.utils.MessageConst;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class CustomResponseService {
    public CustomResponse savedResponse(Object object){
        return new CustomResponse(
                MessageConst.SAVED_MESSAGE,true,201,getNow(),object
        );
    }
    public CustomResponse successResponse(Object object){
        return new CustomResponse(
                MessageConst.GET_SUCCESS,true,200,getNow(),object
        );
    }
    public CustomResponse existsResponse(){
        return new CustomResponse(
                MessageConst.EXISTS_MESSAGE,false,409,getNow()
        );
    }
    public CustomResponse objectsNotSameResponse(){
        return new CustomResponse(
                MessageConst.NOT_SAME_MESSAGE,false,409,getNow()
        );
    }


    public CustomResponse tryErrorResponse(){
        return new CustomResponse(
                MessageConst.TRY_ERROR_MESSAGE,false,409,getNow()
        );
    }

    public CustomResponse orderNotResponse(){
        return new CustomResponse(
                MessageConst.ORDER_NOT_MESSAGE,false,401,getNow()
        );
    }

    private Timestamp getNow(){
        return Timestamp.valueOf(LocalDateTime.now());
    }
}
