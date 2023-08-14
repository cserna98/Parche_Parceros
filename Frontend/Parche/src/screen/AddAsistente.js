import React, { useState, useEffect } from 'react';
import { View, StyleSheet } from 'react-native';
import { TextInput, Button } from 'react-native-paper';
import { postAsistente } from '../api/AsistenteApi'; // Importa la función de la API para el POST de asistentes
import StatusBarMargin from '../components/StatusBarMargin';

const AddAsistente = (props) => {


  const [nombre, setNombre] = useState('');
  const [dias, setDias] = useState('');
  const [idParche, setIdParche] = useState(0); // Cambia esto según tu lógic

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
    console.log(nombre)
    const asistente = {
      name: nombre,
      dias: parseInt(dias),
      idParche: idParche,
    };
  


    postAsistente(asistente); // Realiza el POST del asistente utilizando la función de la API
  };


  return (
    <StatusBarMargin>
      <View style={styles.container}>
      <TextInput
        label="Nombre"
        value={nombre}
        onChangeText={text => setNombre(text)}
        style={styles.input}
      />
      <TextInput
        label="Días"
        value={dias}
        onChangeText={text => setDias(text)}
        keyboardType="numeric"
        style={styles.input}
      />
      {/* Otros campos necesarios */}
      
      <Button mode="contained" onPress={handleCrearAsistente} style={styles.button}>
        Crear Asistente
      </Button>
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
});

export default AddAsistente;
