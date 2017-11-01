if [ -f "src/Calculator.class" ]
then
    java -cp :src/forms_rt.jar:src: Calculator
else
    echo "Main class not found. Try running make"
fi
