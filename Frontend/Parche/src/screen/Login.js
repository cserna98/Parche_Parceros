import React, { useState } from 'react';
import { useNavigation } from "@react-navigation/native";
import { View, Text, TextInput, Button, StyleSheet, TouchableOpacity } from 'react-native';
import { AuthLogin } from '../api/UsuarioApi'
import { useAuth } from '../Context/AuthContext';

const Login = ({ navigation }) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [login, setlogin] = useState(null);
  const { setAuthToken } = useAuth();

  const navigate = useNavigation();

  const handleLogin = () => {

    const credentials = {
        email : email,
        password: password,
    };
    // Realizar la solicitud POST a /api/auth/login con email y password
    AuthLogin(credentials)
    .then((response) => {
      // Aquí puedes realizar cualquier acción adicional según la respuesta
      if (response.status === 200) {
        // Inicio de sesión exitoso
        setlogin(true);

        setTimeout(() => {
          setAuthToken(response.headers.map.authorization)
          setlogin(false);
          navigate.navigate('Parches'); // Navega a ParchesInfo
        }, 1000);
      } else if (response.status === 401) {
        // Credenciales inválidas
        console.log('Credenciales inválidas. Por favor, verifica tus datos de inicio de sesión.');
      } else {
        // Otro caso de error
        console.error('Error desconocido al iniciar sesión. Estado:', response.status);
      }
    })
    .catch((error) => {
      console.error('Error al iniciar sesión:', error);
    });
    // Aquí debes implementar la lógica para enviar la solicitud al servidor
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Iniciar Sesión</Text>
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
      <Button title="Iniciar Sesión" onPress={handleLogin} />
      <TouchableOpacity onPress={() => navigation.navigate('Registro')}>
        <Text style={styles.registerLink}>¿No tienes una cuenta? Regístrate aquí</Text>
      </TouchableOpacity>
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
  registerLink: {
    marginTop: 20,
    color: 'blue',
  },
});

export default Login;