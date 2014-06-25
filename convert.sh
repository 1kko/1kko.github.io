#!/bin/bash
datapath=$(pwd)/menu
/usr/bin/iconv -f euc-kr -t utf-8 $datapath/csv_export.csv > $datapath/csv_export_utf8.csv
