import React, { useState, useEffect } from 'react';
import { View, StyleSheet } from 'react-native';
import { Text, TextInput, Button } from 'react-native-paper';
import { postAsistente } from '../api/AsistenteApi'; // Importa la función de la API para el POST de asistentes
import StatusBarMargin from '../components/StatusBarMargin';
import { useAuth } from '../Context/AuthContext';

const AddAsistente = (props) => {

  const [name, setname] = useState('');
  const [email, setEmail] = useState('');
  const [dias, setDias] = useState('');
  const [idParche, setIdParche] = useState(0); // Cambia esto según tu lógic
  const [asistenteCreado, setAsistenteCreado] = useState(false); // Estado para controlar la visualización del mensaje
  const { token, setAuthToken } = useAuth();

  
  const {
    navigation,
    route: {params},
  } = props;

  useEffect(() => {
    console.log("holi params"+params)
    if (params) {
        setIdParche(params.idParche);
        console.log(params.idParche);
    }
}),[params];


  const handleCrearAsistente = () => {
  
    const asistente = {
      nombre:name,
      email: email,
      dias: parseInt(dias),
      idParche: idParche,
    };

    postAsistente(asistente, idParche, token)
      .then((response) => {
        console.log(response)
        if (response.status === 200) {
          // Inicio de sesión exitoso
          setAsistenteCreado(true); // Mostrar el mensaje de éxito
        setTimeout(() => {
          setAsistenteCreado(false); // Ocultar el mensaje después de 3 segundos
          navigation.goBack(); // Regresar a la pantalla anterior
        }, 1000);
      
        } else if (response.status === 401) {
          // Credenciales inválidas
          console.log('Credenciales inválidas. Por favor, verifica tus datos de inicio de sesión.');
        } else {
          // Otro caso de error
          console.error('Error desconocido al crear asistente. Estado:', response.status);
        }
        
      })
      .catch((error) => {
        console.error('Error al crear el asistente', error);
      });
  };


  return (
    <StatusBarMargin>
      <View style={styles.container}>
      <TextInput
          label="name"
          value={name}
          onChangeText={(text) => setname(text)}
          style={styles.input}
        />
        <TextInput
          label="email"
          value={email}
          onChangeText={(text) => setEmail(text)}
          style={styles.input}
        />
        <TextInput
          label="Días"
          value={dias}
          onChangeText={(text) => setDias(text)}
          keyboardType="numeric"
          style={styles.input}
        />
        {/* Otros campos necesarios */}
        
        <Button mode="contained" onPress={handleCrearAsistente} style={styles.button}>
          Crear Asistente
        </Button>

        {asistenteCreado && (
          <View style={styles.messageContainer}>
            <Text style={styles.message}>Asistente creado con éxito</Text>
          </View>
        )}
      </View>
    </StatusBarMargin>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    justifyContent: 'center',
  },
  input: {
    marginBottom: 20,
  },
  button: {
    marginTop: 20,
  },
  messageContainer: {
    position: 'absolute',
    top: '33%', // Aproximadamente un tercio del alto de la pantalla
    left: '10%', // 80% del ancho de la pantalla
    right: '10%',
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: 'rgba(0, 0, 0, 0.7)',
    padding: 20,
    borderRadius: 10,
    zIndex: 1000,
  },
  message: {
    color: 'white',
    fontSize: 18,
    fontWeight: 'bold',
    textAlign: 'center',
  },
});

export default AddAsistente;
