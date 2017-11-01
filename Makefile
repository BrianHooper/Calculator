#Compile classes
default: 
	javac -cp src/forms_rt.jar src/*.java 

# Remove compiled classes
clean: 
	$(RM) src/*.class
