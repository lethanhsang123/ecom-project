package com.personal.project.common.validator;

public interface ValidateConstraint {

    interface LENGTH {
        int ID_MAX_LENGTH = 36;
        int NAME_MAX_LENGTH = 100;
        int TITLE_MAX_LENGTH = 150;
        int DESCRIPTION_MAX_LENGTH = 200;
        int ENUM_MAX_LENGTH = 20;
        int CODE_MAX_LENGTH = 20;
    }

    interface FORMAT {

    }

}
