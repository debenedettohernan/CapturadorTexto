import React, { useState } from "react";
import axios from "axios";

const ImageUploader: React.FC = () => {
  const [file, setFile] = useState<File | null>(null);
  const [imageUrl, setImageUrl] = useState<string>("");
  const [text, setText] = useState<string>("");
  const [previewUrl, setPreviewUrl] = useState<string | null>(null);

  // Manejar el archivo cargado
  const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const selectedFile = event.target.files?.[0];
    setFile(selectedFile || null);
    if (selectedFile) {
      const objectUrl = URL.createObjectURL(selectedFile);
      setPreviewUrl(objectUrl);
    }
  };

  // Manejar la URL ingresada
  const handleUrlChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setImageUrl(event.target.value);
  };

  // Procesar imagen desde archivo
  const handleUpload = async () => {
    if (file) {
      const formData = new FormData();
      formData.append("img", file);

      try {
        const response = await axios.post("http://localhost:8080/ocr", formData);
        setText(response.data);
      } catch (error) {
        console.error("Error al procesar la imagen:", error);
      }
    }
  };

  // Procesar imagen desde URL
  const handleUploadFromUrl = async () => {
    if (imageUrl) {
      try {
        const response = await axios.post("http://localhost:8080/ocr-url", imageUrl, {
          headers: { "Content-Type": "text/plain" },
        });
        setPreviewUrl(imageUrl);
        setText(response.data);
      } catch (error) {
        console.error("Error al procesar la imagen desde la URL:", error);
      }
    }
  };

  return (
    <div>
      <h2>OCR Frontend</h2>

      {/* Subir imagen desde archivo */}
      <input type="file" onChange={handleFileChange} />
      <button onClick={handleUpload}>Procesar Imagen</button>

      {/* Cargar imagen desde URL */}
      <input
        type="text"
        placeholder="Ingresar URL de la imagen"
        value={imageUrl}
        onChange={handleUrlChange}
      />
      <button onClick={handleUploadFromUrl}>Procesar Imagen desde URL</button>

      {/* Mostrar imagen */}
      {previewUrl && <img src={previewUrl} alt="Preview" width="400px" />}

      {/* Mostrar texto capturado */}
      <h3>Texto Capturado:</h3>
      <p>{text}</p>
    </div>
  );
};

export default ImageUploader;
