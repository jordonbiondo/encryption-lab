
# encrypty build
env = Environment(tools=['javac', 'jar'])

env.Java(target='classes', source='src')

env.Jar(target='bin/encrypty.jar', 
        source=['classes', 'EncryptyManifest.txt'],
        JARCHDIR='$SOURCE')

env.Jar(target='bin/decrypty.jar', 
        source=['classes', 'DecryptyManifest.txt'],
        JARCHDIR='$SOURCE')

