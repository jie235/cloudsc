drop table if exists scdemo."undo_log";

create table scdemo."undo_log"
(
    "id"            serial       not null,
    "branch_id"     bigint       not null,
    "xid"           varchar(100) not null,
    "context"       varchar(128) not null,
    "rollback_info" bytea        not null,
    "log_status"    int          not null,
    "log_created"   timestamp    not null,
    "log_modified"  timestamp    not null,
    "ext"           varchar(100) default null,
    constraint "ux_primary_id" primary key ("id"),
    constraint "ux_undo_log" unique ("xid", "branch_id")
);