import React, { useState } from 'react';
import { View, StyleSheet } from 'react-native';
import { TextInput, Button } from 'react-native-paper';
import DatePicker from 'react-native-datepicker';
import { useNavigation } from "@react-navigation/native";

const AddParche = (props) => {
  const [nombre, setNombre] = useState('');
  const [fecha, setFecha] = useState(new Date());
  const [dias, setDias] = useState('');
  const [asistentes, setAsistentes] = useState([]); // Array de nombres de asistentes


  const {
    navigation,
    route: {params},
  } = props;

  const handleCrearParche = () => {

   
    const parcheDTO = {
      nombre: nombre,
      fecha: fecha.toISOString(),
      dias: parseInt(dias),
      nombresAsistentes: asistentes, // Asignar el array de asistentes al parcheDTO
    };

    // Aquí puedes hacer el POST con el JSON de parcheDTO al backend...
  };

  const handleAgregarAsistente = () => {
    // Agregar un nuevo asistente al array de asistentes
    setAsistentes([...asistentes, '']);
  };

  const handleChangeAsistente = (index, value) => {
    // Actualizar el nombre del asistente en el array de asistentes
    const nuevosAsistentes = [...asistentes];
    nuevosAsistentes[index] = value;
    setAsistentes(nuevosAsistentes);
  };

  return (
    <View style={styles.container}>
      <TextInput
        label="Nombre"
        value={nombre}
        onChangeText={text => setNombre(text)}
        style={styles.input}
      />
      <DatePicker
        date={fecha}
        mode="date"
        placeholder="Fecha"
        format="YYYY-MM-DD"
        confirmBtnText="Confirmar"
        cancelBtnText="Cancelar"
        customStyles={{
          dateIcon: {
            display: 'none',
          },
          dateInput: styles.dateInput,
        }}
        onDateChange={date => setFecha(date)}
      />
      <TextInput
        label="Días"
        value={dias}
        onChangeText={text => setDias(text)}
        keyboardType="numeric"
        style={styles.input}
      />
      {asistentes.map((asistente, index) => (
        <TextInput
          key={index.toString()}
          label={`Asistente ${index + 1}`}
          value={asistente}
          onChangeText={text => handleChangeAsistente(index, text)}
          style={styles.input}
        />
      ))}
      <Button mode="contained" onPress={handleAgregarAsistente} style={styles.button}>
        Añadir Asistente
      </Button>
      <Button mode="contained" onPress={handleCrearParche} style={styles.button}>
        Crear Parche
      </Button>
    </View>
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
  dateInput: {
    alignItems: 'flex-start',
    borderWidth: 0,
  },
  button: {
    marginTop: 20,
  },
});

export default AddParche;
