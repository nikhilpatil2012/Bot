package com.bumblebee.ResponseToClient;

import com.bumblebee.JSONCreator.Attachment;
import com.bumblebee.JSONCreator.ElementJSONCntlr;
import com.bumblebee.JSONCreator.MasterJSON;
import com.bumblebee.JSONCreator.Payload;
import com.bumblebee.common.utils.Const;

/**
 * Created by deadcode on 01/07/2016.
 */
public class SendTextToClient extends ResponseAction {

    @Override
    public ResponseActionResult execute() {

        MasterJSON masterJSON = new MasterJSON("931411386981115", getText());

        return new ResponseActionResult(masterJSON);
    }
}
