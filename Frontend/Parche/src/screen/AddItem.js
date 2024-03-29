import React, { useState, useEffect } from 'react';
import { View, StyleSheet } from 'react-native';
import { Text,TextInput, Button} from 'react-native-paper';
import { Picker } from "@react-native-picker/picker";
import { postItem } from '../api/ItemApi'; // Importa la función de la API para el POST de items
import StatusBarMargin from '../components/StatusBarMargin';
import { useAuth } from '../Context/AuthContext';

const AddItem = (props) => {
  const [nombre, setNombre] = useState('');
  const [descripcion, setDescripcion] = useState('');
  const [dia, setDia] = useState('');
  const [img, setImg] = useState('');
  const [costo, setCosto] = useState('');
  const [nombreAsistente, setNombreAsistente] = useState('');
  const [itemCreado, setItemCreado] = useState(false);
  const [listaAsistentes, setListaAsistentes] = useState([])
  const { token, setAuthToken } = useAuth();


  const {
    navigation,
    route: {params},
  } = props;

  useEffect(()=>{
    setListaAsistentes(params.asistentes)
  },[])

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
    postItem(item,token)
      .then(() => {
        setItemCreado(true);
        setTimeout(() => {
          setItemCreado(false);
          navigation.goBack();
        }, 3000);
      })
      .catch((error) => {
        console.error('Error al crear el ítem', error);
      }); 
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
        {listaAsistentes?.map(asistente => (
          <Picker.Item key={asistente.id} label={asistente.nombre} value={asistente.nombre} />
        ))}
      </Picker>

      {/* Otros campos necesarios */}
      <Button mode="contained" onPress={handleCrearItem} style={styles.button}>
        Crear Item
      </Button>
      {itemCreado && (
          <View style={styles.messageContainer}>
            <Text style={styles.message}>Ítem creado con éxito</Text>
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

export default AddItem;
