export default function CatCard({ imageUrl }) {
    return (
      <div className="cat-card">
        <img 
          src={imageUrl} 
          alt="Gatinho fofo" 
          className="cat-image"
          onError={(e) => {
            e.target.src = 'https://placekitten.com/500/500'
          }}
        />
      </div>
    )
  }