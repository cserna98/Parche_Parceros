import React, { useState } from 'react';
import { View, Text, TextInput, Button, StyleSheet } from 'react-native';
import { useNavigation } from "@react-navigation/native";
import {AuthRegister} from '../api/UsuarioApi';


const Registro = () => {

  const navigate = useNavigation();
    
  const [firstname, setFirstname] = useState('');
  const [lastname, setLastname] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [usuarioRegistrado, setUsuarioRegistrado] = useState(null);




  const handleRegistro = () => {
    
    try {
      // Los datos del usuario que deseas registrar
      const userData = {
          firstname: firstname,
          lastname: lastname,
          email: email,
          password: password,
          roles: [
              {
                  name: "USER"
              }
          ]
      };

      // Llamar a la función AuthRegister para registrar al usuario
    AuthRegister(userData)
    .then((response) =>{
      console.log(response)
      if (response.status === 200) {
        setUsuarioRegistrado(true)
        setTimeout(() => {
          
          navigate.navigate('Login'); // Navega a ParchesInfo
        }, 1000);
        console.log('Usuario registrado con éxito');
    } else {
        // Manejar errores de registro aquí
        console.error('Error al registrar usuario');
    }
    })

  } catch (error) {
      console.error('Error al registrar usuario:', error);
  }
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Registro de Usuario</Text>
      <TextInput
        placeholder="Nombre"
        onChangeText={(text) => setFirstname(text)}
        value={firstname}
        style={styles.input}
      />
      <TextInput
        placeholder="Apellido"
        onChangeText={(text) => setLastname(text)}
        value={lastname}
        style={styles.input}
      />
      <TextInput
        placeholder="Correo Electrónico"
        onChangeText={(text) => setEmail(text)}
        value={email}
        style={styles.input}
      />
      <TextInput
        placeholder="Contraseña"
        onChangeText={(text) => setPassword(text)}
        value={password}
        secureTextEntry
        style={styles.input}
      />
      <Button title="Registrarse" onPress={handleRegistro} />
      {usuarioRegistrado && (
          <View style={styles.messageContainer}>
            <Text style={styles.message}>Usuario registrado con éxito</Text>
          </View>
        )}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 20,
  },
  input: {
    width: '80%',
    height: 40,
    borderColor: 'gray',
    borderWidth: 1,
    borderRadius: 5,
    marginBottom: 15,
    paddingLeft: 10,
  },
});

export default Registro;
