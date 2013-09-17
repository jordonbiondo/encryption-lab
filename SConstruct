env = Environment(tools=['javac', 'jar'])
env.Java(target='classes', source='src')
env.Jar(target='bin/encrypty.jar', source=['classes', 'Manifest.txt'],
        JARCHDIR='$SOURCE')
