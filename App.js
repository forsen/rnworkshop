/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, { useState } from "react";
import { Button, NativeModules, StyleSheet, Text, View } from "react-native";

const { Biometrics } = NativeModules;

const handleButtonPress = async setText => {
  try {
    const result = await Biometrics.authenticate();
    setText(result);
  } catch (error) {
    setText(error.message);
  }
};

const App = () => {
  const [text, setText] = useState("Ikke validert");

  return (
    <View style={styles.container}>
      <Text style={styles.text}>{text}</Text>
      <Button title="Biometri" onPress={() => handleButtonPress(setText)} />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center"
  },
  text: {
    fontSize: 64
  }
});

export default App;
