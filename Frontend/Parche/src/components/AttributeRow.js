import React from 'react';
import { View, StyleSheet } from 'react-native';
import { Text } from 'react-native-paper';



const AttributeRow = (attributeName, attributeValue) => {

    return (
        <View style={styles.attributeRow}>
            <Text style={styles.attribute}>{attributeName}:</Text>
            <Text style={styles.value}>{attributeValue}</Text>
        </View>
        );
    };

    const styles = StyleSheet.create({
        attributeRow: {
            flexDirection: 'row',
            justifyContent: 'space-between',
            marginBottom: 5,
      },
      attribute: {
        fontWeight: 'bold',
        fontSize: 18,
      },
    });

export default AttributeRow;
