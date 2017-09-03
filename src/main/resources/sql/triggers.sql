create trigger signal_value_insert
after insert on signal_value_ext
  for each ROW
  EXECUTE PROCEDURE processSignalValueExt();