CREATE TABLE mysocialnetwork.REQUESTS_TO_JOIN_GROUP_TBL
(
    ACC_ID INT NOT NULL,
    GR_ID INT NOT NULL,
    CONSTRAINT REQUESTS_TO_JOIN_GROUP_fk PRIMARY KEY (ACC_ID, GR_ID),
    CONSTRAINT REQUESTS_TO_JOIN_GROUP_TBL_ACC_ID_fk FOREIGN KEY (ACC_ID) REFERENCES ACCOUNT_TBL (ACC_ID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT REQUESTS_TO_JOIN_GROUP_TBL_GR_ID_fk FOREIGN KEY (GR_ID) REFERENCES GROUP_TBL (GR_ID) ON DELETE CASCADE ON UPDATE CASCADE
);