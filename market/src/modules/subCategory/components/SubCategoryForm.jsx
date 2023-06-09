import React,{ useEffect,useState } from "react";
import { useFormik } from "formik";
import { Button, Col, Row, Form, Modal, Select } from "react-bootstrap";
import * as yup from "yup";
import AxiosClient from "../../../shared/plugins/axios";
import FeatherIcon from "feather-icons-react";
import Alert, {
  confirmMsj,
  confirmTitle,
  successMsj,
  successTitle,
  errorMsj,
  errorTitle,
} from "../../../shared/plugins/alerts";

export const SubCategoryForm = ({ isOpen, setSubcategories, onClose }) => {
    const [categories, setCategories] = useState([])

    const getCategories = async () => {
        try {

            const data = await AxiosClient({ url: '/category/' })
            if (!data.error) setCategories(data.data)
        } catch (error) {
           //alerta de error
        } finally {

        }
    }
    useEffect(() => {
        getCategories()
    }, [])

  const form = useFormik({
    initialValues: {
      name: "",
      status: true,
      category_id: 0
    },
    validationSchema: yup.object().shape({
      name: yup
        .string()
        .required("Campo obligatorio")
        .min(4, "Mínimo 4 caracteres"),
    }),
    onSubmit: async (values) => {
      
      return Alert.fire({
        title: confirmTitle,
        text: confirmMsj,
        icon: "warning",
        confirmButtonColor: "#009574",
        confirmButtonText: "Aceptar",
        cancelButtonColor: "#DD6B55",
        cancelButtonText: "Cancelar",
        reverseButtons: true,
        backdrop: true,
        showCancelButton: true,
        showLoaderOnConfirm: true,
        allowOutsideClick: () => !Alert.isLoading,
        preConfirm: async () => {
          try {
            console.log('Values',values);
            const response = await AxiosClient({
              method: "POST",
              url: "/subcategory/",
              data: JSON.stringify(values),
            });
            if (!response.error) {
              setSubcategories((subcategories) => [response.data, ...subcategories]);
              Alert.fire({
                title: successTitle,
                text: successMsj,
                icon: "success",
                confirmButtonColor: "#3085d6",
                confirmButtonText: "Aceptar",
              }).then((result) => {
                if (result.isConfirmed) {
                  handleClose();
                }
              });
            }
            return response;
          } catch (error) {
            Alert.fire({
              title: errorTitle,
              text: errorMsj,
              icon: "error",
              confirmButtonColor: "#3085d6",
              confirmButtonText: "Aceptar",
            }).then((result) => {
              if (result.isConfirmed) {
                handleClose();
              }
            });
          }
        },
      });
    },
  });

  const handleClose = () => {
    form.resetForm();
    onClose();
  };

  return (
    <Modal
      backdrop="static"
      keyboard={false}
      show={isOpen}
      onHide={handleClose}
    >
      <Modal.Header closeButton>
        <Modal.Title>Registrar subcategoria</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form onSubmit={form.handleSubmit}>
          <Form.Group className="mb-3">
            <Form.Label>Nombre</Form.Label>
            <Form.Control
              name="name"
              placeholder="zapatos"
              value={form.values.name}
              onChange={form.handleChange}
            />
            {form.errors.name && (
              <span className="error-text">{form.errors.name}</span>
            )}
          </Form.Group>
          <Form.Group className="mb-3">
            <Form.Control as="select"
                name="category_id"
                value={form.values.category_id}
                onChange={form.handleChange}
            >
                <option>Selecciona una categoria</option>
                {categories.map(category_id => (
                    <option key={category_id.id} value={category_id.id} onChange={form.handleChange}>{category_id.name}</option>
                ))}
            </Form.Control>
          </Form.Group>
          <Form.Group className="mb-3">
            <Row>
              <Col className="text-end">
                <Button
                  className="me-2"
                  variant="outline-danger"
                  onClick={handleClose}
                >
                  <FeatherIcon icon="x" /> &nbsp;Cerrar
                </Button>
                <Button
                  type="submit"
                  variant="outline-success"
                >
                  <FeatherIcon icon="check" /> &nbsp;Guardar
                </Button>
              </Col>
            </Row>
          </Form.Group>
        </Form>
      </Modal.Body>
    </Modal>
  );
};