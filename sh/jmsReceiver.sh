#!/bin/sh

# Program Name (Class Name)
PROGRAM_NAME=io.jms.receiver.main.ReceiverMain

# Define Environment Variable
JAVA_HOME=/usr/local/java
JMS_HOME=/usr/local/app/jms
export JAVA_HOME
LOG_FILE=${JMS_HOME}/logs/jmsReceiver.log
STOP_FILE_PATH=${JMS_HOME}/stop.txt

# Set Classpath
for f in `find ${JMS_HOME}/libs -type f -name "*.jar"`
do
   CLASSPATH=$CLASSPATH:$f
done

# Change Directory
cd ${JMS_HOME}/classes

# JAVA Execute
if [ -e ${STOP_FILE_PATH} ];
then
    rm -f ${STOP_FILE_PATH}
fi

${JAVA_HOME}/bin/java -classpath ${CLASSPATH} ${PROGRAM_NAME} >> ${LOG_FILE}

exit 0