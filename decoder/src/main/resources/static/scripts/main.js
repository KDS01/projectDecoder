// // document.addEventListener('mousemove', function(e) {
// //     const mouseX=e.clientX;
// //     const mouseY=e.clientY;

// //     const particleContainer=document.getElementById('particles-js');
// //     const rect=particleContainer.getBoundingClientRect();
// //     const centerX=rect.left+rect.width/2;
// //     const centerY=rect.top+rect.height/2;
// //     const angle=Math.atan2(mouseY-centerY,mouseX-centerX);
// //     const rotation=angle *(180/Math.PI);

// //     particleContainer.style.transform=`rotate(${rotation}deg)`;

// // });
// const scene = new THREE.Scene();
// const camera = new THREE.PerspectiveCamera(50, window.innerWidth / window.innerHeight, 0.1, 1000);
// camera.position.z = 11;

// const renderer = new THREE.WebGLRenderer({ alpha: true });
// renderer.setSize(window.innerWidth, window.innerHeight);
// renderer.setClearColor(0x000000, 0);
// document.getElementById('particles-container').appendChild(renderer.domElement);

// const particlesCount = 13;
// const particlesGeometry = new THREE.BufferGeometry();
// const particlesMaterial = new THREE.PointsMaterial({
//   color: 0x000000,
//   size: 0.1
// });

// const particlesPositions = new Float32Array(particlesCount * 3);
// const linesPositions = new Float32Array(particlesCount * 3 * 2);

// for (let i = 0; i < particlesCount * 3; i++) {
//     const randomValue = (Math.random() - 0.5) * 10;
//     particlesPositions[i] = randomValue;
//     linesPositions[i] = randomValue;
//   }

// console.log('Particles Positions:', particlesPositions);

// particlesGeometry.setAttribute('position', new THREE.BufferAttribute(particlesPositions, 3));
// const particles = new THREE.Points(particlesGeometry, particlesMaterial);
// scene.add(particles);

// const linesMaterial = new THREE.LineBasicMaterial({
//   color: 0x000000,
//   linewidth: 4,
// });

// const linesGeometry = new THREE.BufferGeometry();


// console.log('Particles Positions for Lines:', linesPositions);

// linesGeometry.setAttribute('position', new THREE.BufferAttribute(linesPositions, 3));
// const lines = new THREE.LineSegments(linesGeometry, linesMaterial);
// scene.add(lines);

// const mouse = new THREE.Vector2();
// const windowHalf = new THREE.Vector2(window.innerWidth / 2, window.innerHeight / 2);

// document.addEventListener('mousemove', (event) => {
//   mouse.x = (event.clientX / window.innerWidth) * 2 - 1;
//   mouse.y = -(event.clientY / window.innerHeight) * 2 + 1;
// });


// const animate = function () {
//   requestAnimationFrame(animate);


//   scene.rotation.x = mouse.y * 0.15;
//   scene.rotation.y = mouse.x * 0.15;


//   const positions = particlesGeometry.attributes.position.array;

//   for (let i = 0; i < particlesCount; i++) {
//     linesPositions[i * 6] = positions[i * 3];
//     linesPositions[i * 6 + 1] = positions[i * 3 + 1];
//     linesPositions[i * 6 + 2] = positions[i * 3 + 2];

//     linesPositions[i * 6 + 3] = positions[i * 3];
//     linesPositions[i * 6 + 4] = positions[i * 3 + 1];
//     linesPositions[i * 6 + 5] = positions[i * 3 + 2];
//   }

//   linesGeometry.attributes.position.needsUpdate = true;

//   renderer.render(scene, camera);
// };

// animate();



const renderer = new THREE.WebGLRenderer();
renderer.setSize( window.innerWidth, window.innerHeight );
renderer.setClearColor(0xffffff, 0);
document.body.appendChild( renderer.domElement );




const camera = new THREE.PerspectiveCamera(50, window.innerWidth / window.innerHeight, 0.1, 1000);
camera.position.set( 0, 0, 100 );
camera.lookAt( 0, 0, 0 );

const scene = new THREE.Scene();
const material = new THREE.LineBasicMaterial( { color: 0x000000 } );
const points = [];

for(let i=0; i<13; i++){
  let randomizeX=(Math.random()*30);
  let randomizeY=(Math.random()*30);
  let randomizeZ=(Math.random()*30);
  points.push(new THREE.Vector3(randomizeX,randomizeY,randomizeZ));
}

const geometry = new THREE.BufferGeometry().setFromPoints( points );
const line = new THREE.Line( geometry, material );
scene.add( line );
renderer.render( scene, camera );
// const mouse = new THREE.Vector2();
// const windowHalf = new THREE.Vector2(window.innerWidth / 2, window.innerHeight / 2);

// document.addEventListener('mousemove', (event) => {
//   mouse.x = (event.clientX / window.innerWidth) * 2 - 1;
//   mouse.y = -(event.clientY / window.innerHeight) * 2 + 1;
// });


// const animate = function () {
//   requestAnimationFrame(animate);


//   scene.rotation.x = mouse.y * 0.15;
//   scene.rotation.y = mouse.x * 0.15;


//   const positions = particlesGeometry.attributes.position.array;

//   for (let i = 0; i < particlesCount; i++) {
//     linesPositions[i * 6] = positions[i * 3];
//     linesPositions[i * 6 + 1] = positions[i * 3 + 1];
//     linesPositions[i * 6 + 2] = positions[i * 3 + 2];

//     linesPositions[i * 6 + 3] = positions[i * 3];
//     linesPositions[i * 6 + 4] = positions[i * 3 + 1];
//     linesPositions[i * 6 + 5] = positions[i * 3 + 2];
//   }

//   linesGeometry.attributes.position.needsUpdate = true;

//   renderer.render(scene, camera);
// };

// animate();