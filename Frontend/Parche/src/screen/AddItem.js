import React, { useState } from 'react';
import { View, StyleSheet } from 'react-native';
import { TextInput, Button} from 'react-native-paper';
import { Picker } from "@react-native-picker/picker";
import { postItem } from '../api/ItemApi'; // Importa la función de la API para el POST de items
import StatusBarMargin from '../components/StatusBarMargin';

const AddItem = (props) => {
  const [nombre, setNombre] = useState('');
  const [descripcion, setDescripcion] = useState('');
  const [dia, setDia] = useState('');
  const [img, setImg] = useState('');
  const [costo, setCosto] = useState('');
  const [nombreAsistente, setNombreAsistente] = useState('');

  const {
    navigation,
    route: {params},
  } = props;

  const listaAsistentes = params.asistentes;

  const handleCrearItem = () => {

    const item = {
      nombre: nombre,
      descripcion: descripcion,
      dia: parseInt(dia),
      img: img,
      costo: parseInt(costo),
      idParche: params.idParche,
      nombreAsistente: nombreAsistente,
    };
    console.log(item)
    postItem(item); // Realiza el POST del item utilizando la función de la API
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
        label="Descripción"
        value={descripcion}
        onChangeText={text => setDescripcion(text)}
        style={styles.input}
      />
      <TextInput
        label="Día"
        value={dia}
        onChangeText={text => setDia(text)}
        keyboardType="numeric"
        style={styles.input}
      />
      <TextInput
        label="Imagen"
        value={img}
        onChangeText={text => setImg(text)}
        style={styles.input}
      />
      <TextInput
        label="Costo"
        value={costo}
        onChangeText={text => setCosto(text)}
        keyboardType="numeric"
        style={styles.input}
      />

      {/* Lista desplegable para nombres de asistentes */}
      <Picker
        selectedValue={nombreAsistente}
        onValueChange={value => setNombreAsistente(value)}
      >
        <Picker.Item label="Selecciona un asistente" value="asistente" />
        {listaAsistentes.map(asistente => (
          <Picker.Item key={asistente.id} label={asistente.nombre} value={asistente.nombre} />
        ))}
      </Picker>

      {/* Otros campos necesarios */}
      <Button mode="contained" onPress={handleCrearItem} style={styles.button}>
        Crear Item
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

export default AddItem;
