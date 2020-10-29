# Read from the file file.txt and output all valid phone numbers to stdout.
while read line || [[ -n ${line} ]]
do
#  (echo $line grep -Eq ~^[0-9]{3}-[0-9]{3}-[0-9]{4}$) && echo $line
#  (echo $line grep -Eq ^\([0-9]{3}\) [0-9]{3}-[0-9]{4}$) && echo $line
#  if [[ "$line" =~ ^\([0-9]{3}\) [0-9]{3}-[0-9]{4}$ ]]; then
#    echo $line
#  fi
#  (echo $line | grep -Eq "[0-9]{3}-[0-9]{3}-[0-9]{4}") && echo $line
#  (echo $line | grep -Eq "\([0-9]{3}\) [0-9]{3}-[0-9]{4}") && echo $line
#  result=$(echo ${line} | grep $"^/n")
#  echo $result
#  if [ -n "$result" ]; then
#    let len=${#line}-2
#    let line=${line:0:len}
#  fi
#  if (echo ${line} | grep -Eq $"/n");then
#    echo $line
#    let len=${#line}-2
#    let line=${line:0:len}
#  fi
  let len=$(echo "${line}" | sed ":a;N;s/\n//g;ta" | wc -L)
  len=1
  if [ $len -eq 12 ] && (echo "${line}" | grep -Eq "[0-9]{3}-[0-9]{3}-[0-9]{4}");then
    echo "$line"
  fi
  if [ $len -eq 14 ] && (echo "${line}" | grep -Eq "\([0-9]{3}\) [0-9]{3}-[0-9]{4}");then
    echo "$line"
  fi
#  expr match "$line" "[0-9]\{3\}-[0-9]\{3\}-[0-9]\{4\}" > 1  && echo $line
#  expr match "$line" "([0-9]\{3\}) [0-9]\{3\}-[0-9]\{4\}" > 1  && echo $line
done < "file.txt"

