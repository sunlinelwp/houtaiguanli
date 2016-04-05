update ap_sys_column set column_type = 'F' where msg_cd = 'qrordrrsp' and column_cd = 'inptam' ;
update ap_sys_column set column_type = 'F' where msg_cd = 'qrordrrsp' and column_cd = 'totlam' ;
update ap_sys_column set column_type = 'F' where msg_cd = 'qrordrrsp' and column_cd = 'ordram' ;
update ap_sys_column set column_type = 'F' where msg_cd = 'qrhpayrsp' and column_cd = 'tranam' ;

commit;
