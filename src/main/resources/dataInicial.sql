INSERT INTO ESPACIO (ID, NAME, DESCRIPTION, PHOTO, AFORO) VALUES
('11111111-1111-1111-1111-111111111111', 'Piscina', 'Piscina principal del conjunto', 'https://images.unsplash.com/photo-1576013551627-0cc20b96c2a7?w=800&q=80', 30),
('22222222-2222-2222-2222-222222222222', 'Salon social', 'Salon para eventos y reuniones', 'https://images.unsplash.com/photo-1519167758481-83f550bb49b3?w=800&q=80', 50),
('33333333-3333-3333-3333-333333333333', 'Cancha multiple', 'Cancha de futbol y baloncesto', 'https://h13n.com/wp-content/uploads/2025/11/2-Medellin-inicia-renovacion-de-cancha-de-futbol-en-barrio-Cristobal.webp', 20),
('44444444-4444-4444-4444-444444444444', 'Gimnasio', 'Gimnasio con maquinas y pesas', 'https://images.unsplash.com/photo-1534438327276-14e5300c3a48?w=800&q=80', 15),
('55555555-5555-5555-5555-555555555555', 'Zona BBQ', 'Zona de asados al aire libre', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR69cHAjo8Gs39IABgYhIflaY_8eYiRg0xxQ52t8axDIMc1eyWxWSw2VHs&s=10', 25);

INSERT INTO USUARIOS (ID, NAME, EMAIL, PASSWORD, ROL) VALUES
('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Kevin Balvin', 'kevin@test.com', '123456', 'ADMINISTRADOR'),
('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Ana Gomez', 'ana@test.com', '123456', 'USUARIO'),
('cccccccc-cccc-cccc-cccc-cccccccccccc', 'Carlos Ruiz', 'carlos@test.com', '123456', 'USUARIO'),
('dddddddd-dddd-dddd-dddd-dddddddddddd', 'Laura Perez', 'laura@test.com', '123456', 'USUARIO');

INSERT INTO RESERVA (ID, "DATE", "TIME", USUARIO_ID, ESPACIO_ID) VALUES
('e1111111-0000-0000-0000-000000000001', '2026-11-05', '10:00:00', 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '11111111-1111-1111-1111-111111111111'),
('e1111111-0000-0000-0000-000000000002', '2026-11-05', '14:00:00', 'cccccccc-cccc-cccc-cccc-cccccccccccc', '11111111-1111-1111-1111-111111111111'),
('e1111111-0000-0000-0000-000000000003', '2026-11-07', '18:00:00', 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '22222222-2222-2222-2222-222222222222'),
('e1111111-0000-0000-0000-000000000004', '2026-11-08', '08:00:00', 'dddddddd-dddd-dddd-dddd-dddddddddddd', '33333333-3333-3333-3333-333333333333'),
('e1111111-0000-0000-0000-000000000005', '2026-11-10', '06:00:00', 'cccccccc-cccc-cccc-cccc-cccccccccccc', '44444444-4444-4444-4444-444444444444'),
('e1111111-0000-0000-0000-000000000006', '2026-11-12', '12:00:00', 'dddddddd-dddd-dddd-dddd-dddddddddddd', '55555555-5555-5555-5555-555555555555');
